package com.af.service;

import com.af.Import1.ImportInitTrue;
import com.af.config.PerSonProperties;
import com.af.config.PerSonYml;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PerSonService {

	@Autowired(required = false)
	private PerSonYml perSonYml;

	@Autowired(required = false)
	private PerSonProperties perSonProperties;

	@Autowired(required = false)
	private ImportInitTrue importInitTrue;

	public void perSonYmlAndProperties(){
		System.out.println("调用perSonYmlAndProperties方法-------不同配置文件，但相同配置项值会覆盖");
		System.out.println(perSonYml);
		System.out.println(perSonProperties);
		importInitTrue.ImportInitTrueTest01();
	}
}
