package com.dd.vbc.network;

import com.dd.vbc.provider.Provider;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * Listing 2.3 ChannelHandler for the client
 *
 * @author <a href="mailto:norman.maurer@gmail.com">Norman Maurer</a>
 */
@Sharable
public class VBCMobileClientHandler extends SimpleChannelInboundHandler<ByteBuf> {

    private final Provider provider;

    public VBCMobileClientHandler(Provider provider) {
        super();
        this.provider = provider;
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        if(ctx!=null) {
            ctx.writeAndFlush(Unpooled.copiedBuffer(NettyMessage.getMessage()));
        } else {
            System.out.println("ChannelHandlerContext is null");
        }
    }

    @Override
    public void channelRead0(ChannelHandlerContext ctx, ByteBuf in) {

        System.out.println("Client received a Server Response ByteBuf capacity: "+in.capacity());
        byte[] bytes = ByteBufUtil.getBytes(in);
        System.out.println("VBCMobileClientHandler.channelRead0 bytes received: "+bytes.length);
        provider.handleServerResponse(ByteBufUtil.getBytes(in));

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}
