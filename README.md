# Kinesis Library Test

Simple repo to test the performance of the kinesis library.

## Usage

1. Run the command line version via `gradle run`
2. Inspect timing. Takes about 2.3 seconds on my laptop
3. build and test locally via `sam build && sam local invoke`
4. Inspect timing. Takes about 2.4 seconds on my laptop
5. Deploy: `sam deploy`
6. Invoke via "Test" panel on Lambda console or CLI, as preferred.
7. Observe time and log:

```
START RequestId: 9ca25a1b-a606-4d8e-8f9d-10ee0d742fce Version: $LATEST
running getInputStreamFromKVS
building AKV client
SLF4J: Failed to load class "org.slf4j.impl.StaticLoggerBinder".
SLF4J: Defaulting to no-operation (NOP) logger implementation
SLF4J: See http://www.slf4j.org/codes.html#StaticLoggerBinder for further details.
built AKV client
elapsed: 22339 milliseconds
building GDER
built GDER
elapsed: 22702 milliseconds
getting endpoint
elapsed: 30141 milliseconds
Exception in thread "main" 
Exception: java.lang.OutOfMemoryError thrown from the UncaughtExceptionHandler in thread "main"
END RequestId: 9ca25a1b-a606-4d8e-8f9d-10ee0d742fce
REPORT RequestId: 9ca25a1b-a606-4d8e-8f9d-10ee0d742fce	Duration: 33277.10 ms	Billed Duration: 33278 ms	Memory Size: 128 MB	Max Memory Used: 116 MB	Init Duration: 394.01 ms	
XRAY TraceId: 1-621fa5b2-1d0fdf726117126372296a3b	SegmentId: 19852da441410609	Sampled: true	
RequestId: 9ca25a1b-a606-4d8e-8f9d-10ee0d742fce Error: Runtime exited with error: exit status 1
Runtime.ExitError
```

I then bumped the memory to 512MB:

```
START RequestId: 782f98ff-780f-4b10-99dd-69732ddb956b Version: $LATEST
running getInputStreamFromKVS
building AKV client
SLF4J: Failed to load class "org.slf4j.impl.StaticLoggerBinder".
SLF4J: Defaulting to no-operation (NOP) logger implementation
SLF4J: See http://www.slf4j.org/codes.html#StaticLoggerBinder for further details.
built AKV client
elapsed: 5003 milliseconds
building GDER
built GDER
elapsed: 5140 milliseconds
getting endpoint
User: arn:aws:sts::124892839813:assumed-role/kinesisLibraryTest-kinesisLibraryTestRole-1DMJWU5RGCWA6/kinesisLibraryTest-kinesisLibraryTest-cy2Toh394KUv is not authorized to perform: kinesisvideo:GetDataEndpoint on resource: foobar (Service: KinesisVideo, Status Code: 403, Request ID: 5c9d9efd-b181-4d8a-b112-54709be68bf7, Extended Request ID: null)
elapsed: 8661 milliseconds
END RequestId: 782f98ff-780f-4b10-99dd-69732ddb956b
REPORT RequestId: 782f98ff-780f-4b10-99dd-69732ddb956b	Duration: 9018.81 ms	Billed Duration: 9019 ms	Memory Size: 512 MB	Max Memory Used: 138 MB	Init Duration: 413.71 ms	
XRAY TraceId: 1-621fa605-43b85fe73f52dd173aa8c9dc	SegmentId: 100f0ec61af028ab	Sampled: true	
```

Still took 9 seconds!