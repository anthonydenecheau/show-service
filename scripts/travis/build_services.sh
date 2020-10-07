echo "Building with travis commit of $BUILD_NAME ..."
mvn clean package -Dmaven.test.skip=true docker:build
