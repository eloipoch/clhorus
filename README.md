# Clhorus experimentation project

[![Build Status](https://travis-ci.org/jordillonch/clhorus.svg?branch=master)](https://travis-ci.org/jordillonch/clhorus)

This project aims to experiment [DDD](http://en.wikipedia.org/wiki/Domain-driven_design) concepts using [Clojure](http://clojure.org) and [Vert.x](http://vertx.io).

Actually code is in a very early stage.


## What are implemented?

- Rest API:
    - Register a new user.

- Repositories:
    - Save a user.

- Tests:
    - User registrator service.


## Installation

- Create a MySQL database called `clhorus_operational_tests`.
- Dump this sql:

```
CREATE TABLE `user` (
  `id` binary(16) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
```

- Configure database connection in clhorus/context/operational/infrastructure/config.clj

- Run Vert.x:

```
lein vertx run
```

- Try:

```
curl http://localhost:8080
curl --data "id=4d09a800-3838-11e4-916c-0800200c9a66" http://localhost:8080/users -v
```

## Test

```
lein midje
```

Enjoy! :)
