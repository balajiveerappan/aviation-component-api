package com.aviation.poc.dao;

import java.util.List;

import com.aviation.poc.vo.ComponentHistoryVO;

public interface ComponentDAO {

	 List<ComponentHistoryVO> getComponentHistoryVO(final List<String> masterId);
}
