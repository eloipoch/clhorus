# Clhorus experimentation project

[![Build Status](https://travis-ci.org/jordillonch/clhorus.svg?branch=master)](https://travis-ci.org/jordillonch/clhorus)

This project aims to experiment [DDD](http://en.wikipedia.org/wiki/Domain-driven_design) concepts using [Clojure](http://clojure.org).


## What are implemented?

- Contexts:
    - Operational (the main context).
    - Analytics.

- Rest API:
    - Register a new user.

- Command bus.

- Domain event publisher.

- Repositories:
    - Save a user.
    - Save a user registration.

- [Component](https://github.com/stuartsierra/component).
    - The use of this library provides a fast workflow as is described in this [post](http://thinkrelevance.com/blog/2013/06/04/clojure-workflow-reloaded).

- RabbitMQ workers.

- Tests:
    - User registrator service.


## Installation

- Create a MySQL database called `clhorus_operational_tests`.
- Dump this SQL:

```
CREATE TABLE `user` (
  `id` binary(16) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
```

- Create a MySQL database called `clhorus_analytics_tests`.
- Dump this SQL:

```
CREATE TABLE `user_registration` (
  `user_id` binary(16) NOT NULL,
  `registration_date` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
```

- Create a MySQL database called `clhorus_analytics_tests`.
- Dump this SQL:

```
CREATE TABLE `domain_event_2` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `aggregate_id` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `data` varchar(21000) NOT NULL,
  `occurred_on` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `in_aggregate_id` (`aggregate_id`),
  KEY `in_name` (`name`),
  KEY `in_occurred_on` (`occurred_on`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8
```

- Configure database connection in:
    - `clhorus/context/operational/infrastructure/config.clj`
    - `clhorus/context/analytics/infrastructure/config.clj`

- Install and run RabbitMQ.

- Run:

`lein run -m clhorus.core`

- Try:

```
curl http://localhost:8080
curl --data "id=4d09a800-3838-11e4-916c-0800200c9a66" http://localhost:8080/users -v
```

## Test

`lein midje`


## Workflow without reload the JVM

The idea behind is to start a REPL just once and write code and test it without reload the JVM.
I used [component](https://github.com/stuartsierra/component) library to achieve that.
For further knowledge read this [post](http://thinkrelevance.com/blog/2013/06/04/clojure-workflow-reloaded).


### Try it

- Start the REPL:

`lein repl`

- On your REPL:

`(go)`

- On other terminal:

`curl http://localhost:8080/foo`

- You get `Not found`

- Now you can change the code to create a new endpoint on the API:

```
clhorus/app/api/routing/users.clj:

(defn routes-users [operational-command-bus]
  (routes
    (GET "/foo" [] "Foo...")
    (GET "/users" [] "Not implemented yet :P")
    (POST "/users" request (users-post operational-command-bus request))))
```

- On your REPL:

`(reset)`

- On the terminal:

`curl http://localhost:8080/foo`

- Now you have a new endpoint working.


## Compile, run and connect to the REPL

```
lein uberjar
java -jar target/clhorus-0.0.1-SNAPSHOT-standalone.jar
lein repl :connect localhost:7888
```


## TODO

- Try [Datomic](http://www.datomic.com).


Enjoy! :)