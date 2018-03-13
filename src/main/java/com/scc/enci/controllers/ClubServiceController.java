package com.scc.enci.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.scc.enci.services.ClubService;
import com.scc.enci.template.ClubObject;
import com.scc.enci.template.ResponseObjectList;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import org.springframework.web.bind.annotation.RequestMethod;

@RestController
@RequestMapping(value="v1/clubs")
@Api(value="club selection", description="Return clubs data")
public class ClubServiceController {
   
	@Autowired
    private ClubService clubService;

    @ApiOperation(value = "View clubs information",response = ResponseObjectList.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved clubs"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })    
    @RequestMapping(value="/getClubs",method = RequestMethod.GET)
    public ResponseObjectList<ClubObject> getClubs() {
        return clubService.getClubs();
    }    
    
}
