package kr.co.platform.util.base;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

public class BaseDAOImpl {
	@Autowired
	protected SqlSessionTemplate sqlSession;
}