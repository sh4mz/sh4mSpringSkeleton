# sh4mSpringSkeleton
Sh4m Spring 4 Skeleton boiler plate

Requirement 
 - Maven
 - Java 8+
 - Tomcat 8.5
 - Eclipse Neon above
 - Database

 Fork from https://github.com/nurkiewicz/spring-data-jdbc-repository and do some major enhancement to support my requirement.
 
 ## Design objectives

* Lightweight, fast and low-overhead. Only a handful of classes, **no XML, annotations, reflection**
* **This is not full-blown ORM**. No relationship handling, lazy loading, dirty checking, caching
* CRUD implemented in seconds
* For small applications where JPA is an overkill
* Use when simplicity is needed or when future migration e.g. to JPA is considered
* Minimalistic support for database dialect differences (e.g. transparent paging of results)

## Features

Each DAO provides built-in support for:

* Mapping to/from domain objects through [`RowMapper`] abstraction
* Generated and user-defined primary keys
* Extracting generated key
* Compound (multi-column) primary keys
* Immutable domain objects
* Paging (requesting subset of results)
* Sorting over several columns (database agnostic)
* Optional support for *many-to-one* relationships
* Supported databases (continuously tested):
	* MySQL
	* PostgreSQL
	* H2
	* HSQLDB
	* Derby
	* MS SQL Server (2008, 2012)
	* Oracle 10g / 11g (9i should work too)
	* ...and most likely many others
* Easy retrieval of records by ID