.PHONY: build-kinesisLibraryTest

build-kinesisLibraryTest:
	gradle build
	unzip -d $(ARTIFACTS_DIR)/ ./build/libs/kinesis*.jar
