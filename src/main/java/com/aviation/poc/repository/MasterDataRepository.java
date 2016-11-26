package com.aviation.poc.repository;

import java.io.Serializable;
import java.util.stream.Stream;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.aviation.poc.vo.MasterData;

public interface MasterDataRepository extends MongoRepository<MasterData, Serializable> {

	@Query("{}")
	public Stream<MasterData> getAllRecords();

	@Query("{master : ?0}")
	public MasterData findByMasterId(String master);

}
