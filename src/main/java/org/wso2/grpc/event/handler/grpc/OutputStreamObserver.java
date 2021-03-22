/*
 * Copyright (c) 2021, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 * WSO2 Inc. licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.wso2.grpc.event.handler.grpc;

import io.grpc.Status;
import io.grpc.StatusRuntimeException;
import io.grpc.stub.StreamObserver;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.wso2.grpc.event.handler.GrpcEventHandler;

/**
 * OutputStreamObserver implements gRPC StreamObserver and overrides methods od StreamObserver.
 */
public class OutputStreamObserver implements StreamObserver<Service.Log> {

    private static Log log = LogFactory.getLog(GrpcEventHandler.class);

    @Override
    public void onNext(Service.Log output) {

        log.info(output.getLog());

    }

    @Override
    public void onError(Throwable t) {

        StatusRuntimeException e = (StatusRuntimeException) t;
        if (e.getStatus().getCode() == Status.Code.DEADLINE_EXCEEDED) {
            log.error("gRPC connection deadline exceeded.", e);
        } else if (e.getStatus().getCode() == Status.Code.UNAVAILABLE) {
            log.error("gRPC service is unavailable.", e);
        } else if (e.getStatus().getCode() == Status.Code.UNIMPLEMENTED) {
            log.error("Operation not implemented in the gRPC service.", e);
        } else if (e.getStatus().getCode() == Status.Code.UNKNOWN) {
            log.error("gRPC server threw unknown exception.", e);
        } else {
            log.error("gRPC service failure. " + e.getStatus().toString());
        }

    }

    @Override
    public void onCompleted() {

    }
}
