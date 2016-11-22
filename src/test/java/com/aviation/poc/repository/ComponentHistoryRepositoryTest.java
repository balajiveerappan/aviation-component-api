package com.aviation.poc.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ComponentHistoryRepositoryTest {

	@Autowired
	private ComponentHistoryRepository histroryRepo;

	@Test
	public void testGetAllComponents() {
		final List<String> componentIdlist =  new ArrayList<>();
		histroryRepo.getAllComponents().
		filter(historyComponent -> 
		componentIdlist.contains(historyComponent.getComponent().getComponentID())).collect(Collectors.toList());
	}

}
