package cqc.satisfaction.common.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cqc.satisfaction.common.service.SecondService;
import cqc.satisfaction.core.service.CommonService;
import cqc.satisfaction.core.service.impl.CommonServiceImpl;

@Service("secondService")
@Transactional
public class SecondServiceImpl extends CommonServiceImpl implements
											SecondService, CommonService
{

}