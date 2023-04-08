# Artifactory URL: https://odo-docker-signed-local.artifactory.oci.oraclecorp.com/webapp/#/packages/docker/hcgbu-ol7-java17
FROM odo-docker-signed-local.artifactory.oci.oraclecorp.com/hcgbu-ol7-java17:0.2.4

USER root

# create main folder
RUN mkdir -p /micronaut

# copy application jar file
COPY target/file-store-*.jar /micronaut/application.jar

# change folder owner to 'opc' user
RUN chown -R opc:opc /micronaut

# Switch user
USER opc
WORKDIR /micronaut

#
# Below lines are used ONLY for documentation purpose, no ports will be exposed
#
# API endpoint
EXPOSE 7070
# health & metrics (admin port)
EXPOSE 7071

ENTRYPOINT ["java", "-jar", "application.jar"]
