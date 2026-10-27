package chnu.edu.websec.repository;

/*
  @author   qwert
  @project   web-sec
  @class  CarRepository
  @version  1.0.0 
  @since 13.10.2026 - 12.58

*/

import chnu.edu.websec.model.Car;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CarRepository extends MongoRepository<Car, String> {
}


 