package kr.co.platform.api.main.dao;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Mapper
public interface ApiMainDAO {

	Map<String, Object> selectMainData(Map<String, Object> dataMap);

}
