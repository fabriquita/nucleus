package org.fabriquita.nucleus.controllers;

import java.util.List;
import java.util.Map;

import org.fabriquita.nucleus.models.Group;
import org.fabriquita.nucleus.services.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/group")
public class GroupController {

    @Autowired
	private GroupService groupService;

	@RequestMapping(value = "/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Group> list() {
	    return groupService.list();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Group get(@PathVariable(value="id") Long id) {
		return groupService.get(id);
	}

	@RequestMapping(value = "/", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Group add(@RequestBody Map<String, Object> data) {
	    String name = (String)data.get("name");
	    Long parentId = new Long((String)data.get("parent_id"));
	    return groupService.add(name, parentId);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Group update(@PathVariable(value="id") Long id, @RequestBody Map<String, Object> data) {
	    String name = (String)data.get("name");
	    Long parentId = new Long((String)data.get("parent_id"));
	    return groupService.update(id, name, parentId);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void delete(@PathVariable(value="id") Long id) {
	    groupService.delete(id);
	}

}
