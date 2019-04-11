package com.xuecheng.xcservicecms.dao;

import com.xuecheng.framework.domain.cms.CmsPage;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by tangjiejin on 2019/03/06
 */
public interface CmsPageRepository extends MongoRepository<CmsPage,String> {

    CmsPage findByPageNameAndSiteIdAndPageWebPath(String pageName,String siteId,String pageWebPath);

}
