/*
 * $ Header: it.geosolutions.georepo.services.rest.RESTUserInterface,v. 0.1 9-set-2011 10.39.58 created by tobaro <tobia.dipisa at geo-solutions.it> $
 * $ Revision: 0.1 $
 * $ Date: 8-set-2011 10.39.58 $
 *
 * ====================================================================
 *
 * Copyright (C) 2007 - 2011 GeoSolutions S.A.S.
 * http://www.geo-solutions.it
 *
 * GPLv3 + Classpath exception
 * 
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. 
 *
 * ====================================================================
 *
 * This software consists of voluntary contributions made by developers
 * of GeoSolutions.  For more information on GeoSolutions, please see
 * <http://www.geo-solutions.it/>.
 *
 */
package it.geosolutions.geostore.services.rest;

import it.geosolutions.geostore.core.model.User;
import it.geosolutions.geostore.services.exception.BadRequestServiceEx;
import it.geosolutions.geostore.services.exception.NotFoundServiceEx;
import it.geosolutions.geostore.services.rest.exception.BadRequestWebEx;
import it.geosolutions.geostore.services.rest.exception.NotFoundWebEx;
import it.geosolutions.geostore.services.rest.model.UserList;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;

import org.apache.cxf.jaxrs.ext.multipart.Multipart;

/** 
 * Interface RESTUserInterface.
 * 
 * @author Tobia di Pisa (tobia.dipisa at geo-solutions.it)
 *
 */
public interface RESTUserService {
	
    @POST
    @Path("/")
    @Consumes({MediaType.APPLICATION_XML, MediaType.TEXT_XML})
    @Produces({MediaType.TEXT_PLAIN, MediaType.TEXT_XML, MediaType.APPLICATION_JSON})
    @RolesAllowed({"ADMIN"})
    long insert(
    		@Context SecurityContext sc, 
    		@Multipart("user") User user) throws BadRequestServiceEx, NotFoundServiceEx;

    @PUT
    @Path("/user/{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.TEXT_XML})
    @RolesAllowed({"ADMIN"})
    long update(
    		@Context SecurityContext sc, 
    		@PathParam("id") long id,
    		@Multipart("user") User user) throws NotFoundWebEx;
    
    @DELETE
    @Path("/user/{id}")
    @RolesAllowed({"ADMIN"})
    void delete(
    		@Context SecurityContext sc, 
    		@PathParam("id") long id) throws NotFoundWebEx;

    @GET
    @Path("/user/{id}")
    @Produces({MediaType.TEXT_PLAIN, MediaType.TEXT_XML, MediaType.APPLICATION_JSON})
    @RolesAllowed({"ADMIN"})
    User get(
    		@Context SecurityContext sc, 
    		@PathParam("id") long id) throws NotFoundWebEx;
    
    @GET
    @Path("/search/{name}")
    @Produces({MediaType.TEXT_PLAIN, MediaType.TEXT_XML, MediaType.APPLICATION_JSON})
    @RolesAllowed({"ADMIN"})
    User get(
    		@Context SecurityContext sc, 
    		@PathParam("name") String name) throws NotFoundWebEx;

    @GET
    @Path("/")
    @RolesAllowed({"ADMIN"})
    UserList getAll(
    		@Context SecurityContext sc, 
            @QueryParam("page") Integer page,
            @QueryParam("entries") Integer entries)throws BadRequestWebEx;

    @GET
    @Path("/count/{nameLike}")
    @RolesAllowed({"ADMIN"})
    long getCount(
    		@Context SecurityContext sc, 
    		@PathParam("nameLike") String nameLike);
    
}