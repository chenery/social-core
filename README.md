social-core
===========

Model users, friends and time-line using spring data and mongodb.

Investigate how easy it would be to add on a NOSQL document store to an existing Spring application.

No transactional support from Mongo, so cannot use transaction management in the same way as Spring JPA.

## Installing and running

- Install Mongodb

See http://docs.mongodb.org/manual/tutorial/install-mongodb-on-os-x/.

    $ brew install mongodb

Follow instructions to load using launchctl.

- Install Maven

    $ mvn test

## Todo
- Publish status updates to friends
- Allow commenting on status updates

## References

### Config

- http://www.infoq.com/articles/spring-data-intro
- http://blog.codecentric.de/en/2012/02/spring-data-mongodb/
- http://www.mongodb.org/display/DOCS/SQL+to+Mongo+Mapping+Chart
- http://www.slideshare.net/robhinds/mongodb-spring-mvc-integration
- http://static.springsource.org/spring-data/data-mongo/docs/1.0.3.RELEASE/apidocs/

### Schema design

- http://www.mongodb.org/display/DOCS/Schema+Design