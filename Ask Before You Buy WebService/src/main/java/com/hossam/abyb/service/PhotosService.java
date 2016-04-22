/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hossam.abyb.service;

import com.hossam.common.dto.businessResponse.ServiceDataWrapperDTO;
import com.hossam.common.dto.businessResponse.ServiceStatusDTO;
import com.hossam.common.util.ValidationUtil;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.stereotype.Service;

/**
 *
 * @author Hossam ElDeen
 */
@Service
@Path("/photos")
public class PhotosService {

    @Context
    private HttpServletRequest request;

    @POST
    @Path("/add")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public ServiceDataWrapperDTO savePhoto() {
        try {
            DiskFileItemFactory factory = new DiskFileItemFactory();
            ServletFileUpload upload = new ServletFileUpload(factory);
            List<FileItem> items = upload.parseRequest(request);
            Iterator<FileItem> iter = items.iterator();
            List<String> paths = new ArrayList<String>();
            while (iter.hasNext()) {
                FileItem item = iter.next();
                if (!item.isFormField()) {
                    if (!item.isFormField()) {
                        String SeqNumber = getNextSequenceNumberForImageUploaded();
                        String path = "C:\\123data\\" + SeqNumber + ".jpg";
                        item.write(new File(path));
                        paths.add(SeqNumber);
                    }
                }
            }
            
            if (paths.size() == 0) {
                return new ServiceDataWrapperDTO(null, "FAILED", "no images have been posted");
            } else {
                return new ServiceDataWrapperDTO(paths, "SUCCESS", "image has been saved successfully");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ServiceDataWrapperDTO(null, "FAILED", "an internal error has been happened");
        }
    }

    private String getNextSequenceNumberForImageUploaded() {
        BufferedReader br = null;
        String sequence = "";
        try {
            br = new BufferedReader(new FileReader("C:\\123data\\sequence.txt"));
            sequence = br.readLine();
            FileWriter fw = new FileWriter("C:\\123data\\sequence.txt");
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write((Integer.parseInt(sequence) + 1) + "");
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return sequence;
    }

    @GET
    @Path("/get")
    @Produces({"image/jpeg", "application/json"})
    public Object getImage(@QueryParam("image_id") String imageId) {
        Map argumentsMap = new HashMap<String, Object>();
        argumentsMap.put("image_id", imageId);
        String argumentsErrorMessage = ValidationUtil.checkExistenceOfArguments(argumentsMap);
        if (argumentsErrorMessage != null) {
            ResponseBuilder response = Response.ok(new ServiceStatusDTO("FAILED", argumentsErrorMessage));
            response.header("content-type", "application/json");
            return response.build();
        }
        
        try {
            File file = new File("C:\\123data\\" + imageId + ".jpg");
            if (!file.exists()) {
                ResponseBuilder response = Response.ok(new ServiceStatusDTO("FAILED", "image_id is not valid"));
                response.header("content-type", "application/json");
                return response.build();
            }
            ResponseBuilder response = Response.ok((Object) file);
            response.header("content-type", "image/jpeg");
            response.header("Accept-Ranges", "bytes");
            return response.build();
        } catch (Exception ex) {
            ResponseBuilder response = Response.ok(new ServiceStatusDTO("FAILED", "an internal error has been happened"));
            response.header("content-type", "application/json");
            return response.build();
        }

//        BufferedImage image = new BufferedImage;
//
//    ByteArrayOutputStream baos = new ByteArrayOutputStream();
//    ImageIO.write(image, "png", baos);
//    byte[] imageData = baos.toByteArray();
        // uncomment line below to send non-streamed
        // return Response.ok(imageData).build();
        // uncomment line below to send streamed
//     return Response.ok(new ByteArrayInputStream(imageData)).build();
    }
}
