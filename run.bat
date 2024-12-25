REM ********************************************************************************************
REM ********    Running Jetty... with debug listening port (4000) to connect Eclipse    ********
REM ********************************************************************************************

set MAVEN_OPTS=-Xms256m -Xmx512m -XX:MaxPermSize=512m -Xdebug -Xnoagent -Djava.compiler=NONE -Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=4000

mvn jetty:run -Djetty.port=8800
