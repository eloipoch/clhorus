# Clhorus experimenting project

[![Build Status](https://travis-ci.org/jordillonch/clhorus.svg?branch=master)](https://travis-ci.org/jordillonch/clhorus)

This project aims to experiment DDD concepts using Clojure.

Actually code is in a very early stage.


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

- Run ring server:

```
lein ring server
```

- Try:

```
curl http://localhost:3000
curl --data "id=4d09a800-3838-11e4-916c-0800200c9a66" http://localhost:3000/users -v
```

## Test

```
lein midje
```

Enjoy! :)
