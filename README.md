<img src="https://github.com/bguerout/jongo/raw/gh-pages/jongo.png" alt="Jongo logo" title="Jongo" align="right">

# Jongo

Jongo is a tiny sugar over Mongo Java Driver:

* Writing `find` queries as if you were in a Mongo Shell
* Mapping Mongo entities to your POJO (with Jackson or a custom Mapper)

## Usage

```java
    Jongo jongo = new Jongo("dbname");
    MongoCollection peoples = jongo.getCollection("peoples");
    
    Iterator<People> all = peoples.find("{}").as(People.class);
    People one = peoples.findOne("{}").as(People.class);
```

## Querying

```java
    //Following samples show usage 
    //1. with Mongo Shell
    //2. with Jongo
    
    //Query with parameters
    db.peoples.find({"name": "Joe"})
    peoples.find("{'name': #}", "Joe").as(People.class);
    
    //Query with ObjectId
    db.peoples.find({"_id": ObjectId("47cc67093475061e3d95369d")})
    peoples.find("{'_id': {$oid: '47cc67093475061e3d95369d'}}").as(People.class);
    
    //Sorting
    db.peoples.find({}).sort({"name": 1})
    peoples.find("{'$query':{}, '$orderby':{'name':1}}").as(People.class);
    
    //Limit
    db.peoples.find().limit(10)
    peoples.find("{'$query':{}, '$maxScan':2}").as(People.class);
    
    //Skip
    db.peoples.find().skip(20)
    Not yet implemented
    
    //Conditional Operators
    db.peoples.find({"age" : {$lt: 3}})
    peoples.find("{'age': {$lt: 3}}").as(People.class);
    
    //Geospacial Operators
    db.peoples.find({"address": {"$near": [0,0], $maxDistance: 5}})
    peoples.find("{'address': {'$near': [0,0], $maxDistance: 5}}").as(People.class);
    
    //Count
    db.peoples.count({"name": "Joe"})
    peoples.count("{'name': 'Joe'}");
    
    //Distinct
    db.peoples.distinct("address");
    peoples.distinct("address", "", Address.class);
    
    //Field Selection
    db.peoples.find({"name": "Joe"}, {"surname": 1})
    peoples.find("{'name': 'Joe'}").on("{'surname': 1}").as(People.class);
```

## Updating

```java
    //Save
    db.peoples.save({"name": "Joe", "surname": "Joseph"})
    peoples.save(new People("Joe", "Joseph"));
    
    //Update
    db.peoples.update({"_id":ObjectId:("47cc67093475061e3d95369d"), "name": "Jack"})
    peoples.save(people.setName("Jack"));
    
    //Update Operators
    db.people.update({"name": "Joe"}, {$inc: {"age": 1}})
    Not yet implemented
    
    //Delete
    db.peoples.remove({"name": "Joe"})
    Not yet implemented
```

## Mapping

To be eligible to Jackson (default) mapping, a class needs a no args constructor (even a `private` one is enough). 

Class field `_id` can be annotated with `javax.persistence.Id` to be renamed. 

```java
    //Custom Mapping
```
## Dev Zone

To get the code and build from source, do the following:

1. git clone  git://github.com/bguerout/jongo.git
2. cd jongo
3. mvn clean install
