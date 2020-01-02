# Run Pace Alexa Skill

A Spring Boot Alexa skill for calculating your run pace

### Installation

1. Run `mvn clean install`
1. Upload `target/run-pace-alexa.jar`, `run-pace-alexa.service`, `run-pace-alexa.conf` and `application-https.yml` to app directory (like `/opt/run-pace-alexa`)
1. Set up keystore with certbot for HTTPS
1. Generate keystore with password: `openssl pkcs12 -export -in fullchain.pem -inkey [privkey.pem] -out keystore.p12 -name tomcat -CAfile chain.pem -caname root`
1. Move keystore to move to `/opt/run-pace-alexa/keystore.p12`
1. Update `application-https.yaml` with `skill.id`, `keystore location`, `keystore password`

For Systemd:
1. Update `run-pace-alexa.conf` with `application-https.yaml` location
1. Update `run-pace-alexa.service` with runtime user name and jar path
1. Create symlink in `/etc/systemd/system` to `run-pace-alexa.service`
1. Start service with `systemctl start run-pace-alexa`

