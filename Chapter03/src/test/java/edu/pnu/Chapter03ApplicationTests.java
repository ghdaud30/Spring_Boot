package edu.pnu;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;

@SpringBootTest
class Chapter03ApplicationTests {
	
	@Autowired
	Environment environment;
	
	@Test
	void contextLoads() {
		System.out.println("테스트 실행");
	}
	
	@DisplayName("속성 출력 테스트")
	@Test
	public void testMethod() {
		System.out.println(environment.getProperty("author.name"));
		System.out.println(environment.getProperty("author.age"));
		System.out.println(environment.getProperty("author.nation"));
	}
					
	
}
