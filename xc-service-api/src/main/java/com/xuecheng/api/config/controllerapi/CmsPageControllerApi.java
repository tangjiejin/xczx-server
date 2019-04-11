package com.xuecheng.api.config.controllerapi;

import com.xuecheng.framework.domain.cms.CmsPage;
import com.xuecheng.framework.domain.cms.request.QueryPageRequest;
import com.xuecheng.framework.domain.cms.response.CmsPageResult;
import com.xuecheng.framework.model.response.QueryResponseResult;

/**
 * Created by tangjiejin on 2019/03/04
 */
public interface CmsPageControllerApi {

    /**
     * 页面的分页查询
     *
     * @param page
     * @param size
     * @param queryPageRequest
     * @return
     */
    QueryResponseResult findList(Integer page, Integer size, QueryPageRequest queryPageRequest);

    /**
     * 添加页面
     *
     * @param cmsPage
     * @return
     */
    CmsPageResult addPage(CmsPage cmsPage);

    /**
     * 编辑页面
     *
     * @param cmsPage
     * @return
     */
    CmsPageResult edit(CmsPage cmsPage);

    /**
     * 删除页面
     *
     * @param pageId
     * @return
     */
    void delete(String pageId);


}
