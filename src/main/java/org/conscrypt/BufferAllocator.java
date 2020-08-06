package org.conscrypt;

import java.nio.ByteBuffer;

/**
 * An object responsible for allocation of buffers. This is an extension point to enable buffer
 * pooling within an application.
 */
public abstract class BufferAllocator {
    private static final BufferAllocator UNPOOLED = new BufferAllocator() {
        @Override
        public AllocatedBuffer allocateDirectBuffer(int capacity) {
            return AllocatedBuffer.wrap(ByteBuffer.allocateDirect(capacity));
        }
    };

    /**
     * Returns an unpooled buffer allocator, which will create a new buffer for each request.
     */
    public static BufferAllocator unpooled() {
        return UNPOOLED;
    }

    /**
     * Allocates a direct (i.e. non-heap) buffer with the given capacity.
     */
    public abstract AllocatedBuffer allocateDirectBuffer(int capacity);
}