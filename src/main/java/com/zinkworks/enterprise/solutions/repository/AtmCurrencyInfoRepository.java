package com.zinkworks.enterprise.solutions.repository;



import org.springframework.data.jpa.repository.JpaRepository;

import com.zinkworks.enterprise.solutions.entity.AtmCurrencyInfo;

public interface AtmCurrencyInfoRepository extends JpaRepository<AtmCurrencyInfo, Integer> {
	
	AtmCurrencyInfo findAtmCurrencyInfoByAtmIdIs(int atmId);

}
