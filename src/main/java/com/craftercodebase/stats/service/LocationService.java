package com.craftercodebase.stats.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.craftercodebase.stats.model.Tbl_Location;
import com.craftercodebase.stats.repository.LocationRepository;

@Service
public class LocationService {
	private static final Logger log = LoggerFactory.getLogger(LocationService.class);

	@Autowired
	LocationRepository locationRepository;

	public Page<Tbl_Location> searchCountries() {

		return null;
	}

}
