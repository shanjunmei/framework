rem call mvn archetype:generate -DgroupId=com.lanhun.framework -DartifactId=framework  -DinteractiveMode=false -DarchetypeCatalog=internal;
set _path=framework
mkdir %_path%
cd %_path%
call mvn archetype:generate -DgroupId=com.lanhun.framework -DartifactId=framework-domain -DarchetypeArtifactId=maven-archetype-quickstart -DinteractiveMode=false -DarchetypeCatalog=internal;
call mvn archetype:generate -DgroupId=com.lanhun.framework -DartifactId=framework-dao -DarchetypeArtifactId=maven-archetype-quickstart -DinteractiveMode=false -DarchetypeCatalog=internal;
call mvn archetype:generate -DgroupId=com.lanhun.framework -DartifactId=framework-service -DarchetypeArtifactId=maven-archetype-quickstart -DinteractiveMode=false -DarchetypeCatalog=internal;
call mvn archetype:generate -DgroupId=com.lanhun.framework -DartifactId=framework-web -DarchetypeArtifactId=maven-archetype-webapp -DinteractiveMode=false -DarchetypeCatalog=internal
rem call mvn eclipse:ecliipse
echo hello