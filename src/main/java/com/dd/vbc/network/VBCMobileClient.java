package com.dd.vbc.network;

import com.dd.vbc.provider.Provider;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.ssl.SslContext;

import java.net.InetSocketAddress;

/**
 * Listing 2.4 Main class for the client
 *
 * @author <a href="mailto:norman.maurer@gmail.com">Norman Maurer</a>
 */
public class VBCMobileClient {
    private final Provider provider;
    private final String host;
    private final int port;

    public VBCMobileClient(Provider provider, String host, int port) {
        this.provider = provider;
        this.host = host;
        this.port = port;
    }

    public Bootstrap start() {
        EventLoopGroup group = new NioEventLoopGroup();
        Bootstrap bootstrap = null;
        try {
            bootstrap = new Bootstrap();
            bootstrap.group(group)
                .channel(NioSocketChannel.class)
                .remoteAddress(new InetSocketAddress(host, port))
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    public void initChannel(SocketChannel ch)
                        throws Exception {
                    ch.config().setRecvByteBufAllocator(new FixedRecvByteBufAllocator(2048));
                    SslHandler sslHandler = new SslHandler();
                    SslContext sslContext = sslHandler.buildSslContext();
                    ch.pipeline().addLast(new VBCMobileClientHandler(provider));
                    ch.pipeline().addLast(sslContext.newHandler(ch.alloc()));
                    }
                });
            ChannelFuture channelFuture = bootstrap.connect().sync();
            channelFuture.channel().closeFuture().sync();
            channelFuture.addListener(ChannelFutureListener.CLOSE);
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        return bootstrap;
    }
}

