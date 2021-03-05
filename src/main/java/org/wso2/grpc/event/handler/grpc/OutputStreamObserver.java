package org.wso2.grpc.event.handler.grpc;

import com.google.protobuf.ByteString;
import io.grpc.stub.StreamObserver;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.wso2.grpc.event.handler.GrpcEventHandler;

import javax.wsdl.Output;

public class OutputStreamObserver implements StreamObserver<Service.Log> {

    private static Log log = LogFactory.getLog(GrpcEventHandler.class);

    @Override
    public void onNext(Service.Log output) {

        log.info(output.getLog());

    }

    @Override
    public void onError(Throwable t) {

    }

    @Override
    public void onCompleted() {

    }
}
