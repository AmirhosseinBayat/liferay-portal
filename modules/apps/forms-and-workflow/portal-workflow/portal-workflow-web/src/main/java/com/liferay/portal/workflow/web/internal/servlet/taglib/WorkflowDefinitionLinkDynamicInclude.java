/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.portal.workflow.web.internal.servlet.taglib;

import com.liferay.portal.kernel.service.WorkflowDefinitionLinkLocalService;
import com.liferay.portal.kernel.servlet.taglib.DynamicInclude;
import com.liferay.portal.workflow.web.internal.constants.WorkflowWebKeys;
import com.liferay.portal.workflow.web.internal.display.context.WorkflowDefinitionLinkDisplayContext;

import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import javax.servlet.ServletContext;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Adam Brandizzi
 */
@Component(
	immediate = true,
	property = {"portal.workflow.tabs.name=" + WorkflowWebKeys.WORKFLOW_TAB_DEFINITION_LINK},
	service = {DynamicInclude.class, WorkflowDynamicInclude.class}
)
public class WorkflowDefinitionLinkDynamicInclude
	extends BaseWorkflowDynamicInclude {

	@Override
	public void prepareRender(
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws PortletException {

		WorkflowDefinitionLinkDisplayContext displayContext =
			new WorkflowDefinitionLinkDisplayContext(
				renderRequest, renderResponse,
				workflowDefinitionLinkLocalService);

		renderRequest.setAttribute(
			WorkflowWebKeys.WORKFLOW_DEFINITION_LINK_DISPLAY_CONTEXT,
			displayContext);
	}

	@Override
	protected String getJspPath() {
		return "/definition_link/view.jsp";
	}

	@Override
	@Reference(
		target = "(osgi.web.symbolicname=com.liferay.portal.workflow.web)",
		unbind = "-"
	)
	protected void setServletContext(ServletContext servletContext) {
		super.setServletContext(servletContext);
	}

	@Reference(unbind = "-")
	protected WorkflowDefinitionLinkLocalService
		workflowDefinitionLinkLocalService;

}