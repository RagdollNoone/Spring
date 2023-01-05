package com.outRead;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import java.util.concurrent.TimeUnit;

public class ServerOutReadHandler extends ChannelOutboundHandlerAdapter {
    private int count;

    @Override
    public void read(ChannelHandlerContext ctx) throws Exception {
        System.out.println("请求读更多的数据，但是我要休息一下");
        TimeUnit.SECONDS.sleep(5);
        super.read(ctx);

//        System.out.println("想读数据，我不同意，我不把请求往前传递");
    }
}
