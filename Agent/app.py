import random
import grpc
import time
from opentelemetry.proto.common.v1 import common_pb2
from opentelemetry.proto.resource.v1 import resource_pb2
from opentelemetry.proto.trace.v1 import trace_pb2
from opentelemetry.proto.collector.trace.v1.trace_service_pb2 import ExportTraceServiceRequest
from opentelemetry.proto.collector.trace.v1.trace_service_pb2_grpc import TraceServiceStub

with grpc.insecure_channel('localhost:4317') as channel:
    stub = TraceServiceStub(channel)
    t = time.time_ns()
    req = ExportTraceServiceRequest(
        resource_spans=[
            trace_pb2.ResourceSpans(
                resource=resource_pb2.Resource(
                    attributes=[
                        common_pb2.KeyValue(
                            key="webengine.name",
                            value=common_pb2.AnyValue(
                                string_value="Flask",
                            ),
                        ),
                        common_pb2.KeyValue(
                            key="webengine.version",
                            value=common_pb2.AnyValue(
                                string_value="1.0.0",
                            ),
                        ),
                        common_pb2.KeyValue(
                            key="service.name",
                            value=common_pb2.AnyValue(
                                string_value="sample-flask",
                            ),
                        ),
                    ],
                ),
                scope_spans=[
                    trace_pb2.ScopeSpans(
                        scope=common_pb2.InstrumentationScope(
                            name="jennifer-test",
                            version="0.0.1",
                        ),
                        spans=[
                            trace_pb2.Span(
                                trace_id=random.getrandbits(128).to_bytes(16, 'big'),
                                span_id=random.getrandbits(64).to_bytes(8, 'big'),
                                name="GET /app",
                                kind=trace_pb2.Span.SPAN_KIND_SERVER,
                                start_time_unix_nano=t,
                                end_time_unix_nano=t+30*1000*1000,
                                attributes=[
                                    common_pb2.KeyValue(
                                        key="http.method",
                                        value=common_pb2.AnyValue(
                                            string_value="GET",
                                        ),
                                    ),
                                    common_pb2.KeyValue(
                                        key="http.status_code",
                                        value=common_pb2.AnyValue(
                                            int_value=200,
                                        ),
                                    ),
                                    common_pb2.KeyValue(
                                        key="net.sock.family",
                                        value=common_pb2.AnyValue(
                                            string_value="inet",
                                        ),
                                    ),
                                    common_pb2.KeyValue(
                                        key="http.flavor",
                                        value=common_pb2.AnyValue(
                                            string_value="1.1",
                                        ),
                                    ),
                                    common_pb2.KeyValue(
                                        key="http.scheme",
                                        value=common_pb2.AnyValue(
                                            string_value="http",
                                        ),
                                    ),
                                    common_pb2.KeyValue(
                                        key="http.target",
                                        value=common_pb2.AnyValue(
                                            string_value="/app",
                                        ),
                                    ),
                                    common_pb2.KeyValue(
                                        key="net.host.name",
                                        value=common_pb2.AnyValue(
                                            string_value="0.0.0.0",
                                        ),
                                    ),
                                ],
                                status=trace_pb2.Status(
                                    message="OK",
                                    code=trace_pb2.Status.STATUS_CODE_OK,
                                )
                            )
                        ]
                    ),
                ],
            ),
        ],
    )
    stub.Export(req)

