/*
 * My-Wine-Cellar, copyright 2020
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package info.mywinecellar.api;

import info.mywinecellar.api.service.WineRestService;
import info.mywinecellar.model.Wine;

import java.io.IOException;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/wine")
public class WineRestController extends AbstractRestController {

    @Inject WineRestService restService;

    /**
     * POST mapping to add a new Wine
     *
     * @param wine       Wine wine
     * @param producerId Long producerId
     * @param shapeId    Long shapeId
     * @param colorId    Long colorId
     * @param typeId     Long typeId
     * @param closureId  Long closureId
     * @return ResponseEntity.CREATED
     */
    @PostMapping("/new")
    public ResponseEntity<?> wineNewPost(@RequestBody Wine wine, @RequestParam Long producerId,
                                         @RequestParam Long shapeId, @RequestParam Long colorId,
                                         @RequestParam Long typeId, @RequestParam Long closureId) {

        Wine saveWine = restService.wineNewSetup(wine, producerId, shapeId, colorId, typeId, closureId);
        checkObjectNull(saveWine);
        wineService.save(saveWine);

        return ResponseEntity.status(HttpStatus.CREATED).body("Created new " + wine.toString());
    }

    /**
     * PUT mapping to edit Wine
     *
     * @param wineId      Long wineId
     * @param wineRequest Wine wineRequest
     * @return ResponseEntity.ACCEPTED
     */
    @PutMapping("/{wineId}/edit")
    public ResponseEntity<?> wineEditPut(@PathVariable Long wineId, @RequestBody Wine wineRequest) {

        Wine wineUpdate = wineService.findById(wineId);
        checkObjectNull(wineUpdate);
        restService.updateWine(wineUpdate, wineRequest);

        return ResponseEntity.accepted().body("Updated " + wineUpdate.toString());
    }

    /**
     * PUT mapping to add image to Wine
     *
     * @param wineId Long wineId
     * @param file   MultipartFile file
     * @return ResponseEntity.ACCEPTED
     * @throws IOException exception
     */
    @PutMapping(value = "/{wineId}/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> wineImagePut(@PathVariable Long wineId, @RequestPart MultipartFile file)
            throws IOException {
        Wine saveWine = wineService.findById(wineId);
        checkObjectNull(saveWine);

        restService.checkFileThenSave(saveWine, file);

        return ResponseEntity.accepted().body("Image upload success");
    }

}
