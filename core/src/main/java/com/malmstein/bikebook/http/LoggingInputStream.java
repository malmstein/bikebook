package com.malmstein.bikebook.http;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * see: http://stackoverflow.com/questions/6043634/is-there-a-simple-tee-filter-for-java-streams
 */
public final class LoggingInputStream extends FilterInputStream {

    private final Logger logger;
    private final Level level;
    private final StringBuilder builder = new StringBuilder();

    public LoggingInputStream(final InputStream in, final Logger logger, final Level level) {
        super(in);
        this.logger = checkNotNull(logger);
        this.level = checkNotNull(level);
    }

    @Override
    public int read() throws IOException {
        int ret = super.read();
        if (ret >= 0) {
            newChar((byte) ret);
        }
        return ret;
    }

    @Override
    public int read(final byte[] buffer, final int offset, final int count) throws IOException {
        int ret = super.read(buffer, offset, count);
        for (int i = 0; i < ret; i++) {
            newChar(buffer[i]);
        }
        return ret;
    }

    private void newChar(final byte ch) {
        builder.append((char) ch);
    }

    @Override
    public void close() throws IOException {
        try {
            super.close();
        } finally {
            logger.log(level, builder.toString());
        }
    }

}
