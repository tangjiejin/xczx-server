package com.xuecheng.xcservicecms.web;

import com.xuecheng.api.config.controllerapi.CmsPageControllerApi;
import com.xuecheng.framework.domain.cms.CmsPage;
import com.xuecheng.framework.domain.cms.request.QueryPageRequest;
import com.xuecheng.framework.domain.cms.response.CmsPageResult;
import com.xuecheng.framework.model.response.QueryResponseResult;
import com.xuecheng.xcservicecms.service.CmsPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by tangjiejin on 2019/03/06
 */
@RestController
public class CmsPageController implements CmsPageControllerApi {

    @Autowired
    CmsPageService cmsPageService;

    @Override
    @GetMapping("/cms/page/findList/{pageIndex}/{pageSize}")
    public QueryResponseResult findList(@PathVariable Integer pageIndex, @PathVariable Integer pageSize, QueryPageRequest queryPageRequest) {

        return cmsPageService.findList(pageIndex, pageSize, queryPageRequest);
    }

    @Override
    @PostMapping("/cms/page/addPage")
    public CmsPageResult addPage(@RequestBody CmsPage cmsPage) {

        return cmsPageService.addPage(cmsPage);
    }

    @Override
    @PostMapping("/cms/page/edit")
    public CmsPageResult edit(@RequestBody CmsPage cmsPage) {

        return cmsPageService.edit(cmsPage);
    }

    @Override
    @GetMapping("/cms/page/delete/{pageId}")
    public void delete(@PathVariable String pageId) {

        cmsPageService.delete(pageId);
    }

}
