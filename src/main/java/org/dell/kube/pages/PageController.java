package org.dell.kube.pages;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import ch.qos.logback.classic.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/pages")
public class PageController {

    Logger logger =(Logger)LoggerFactory.getLogger(this.getClass());

    private IPageRepository pageRepository;
    public PageController(IPageRepository pageRepository)
    {
        this.pageRepository = pageRepository;
    }
    @PostMapping
    public ResponseEntity<Page> create(@RequestBody Page page) {

        logger.info("CREATE-INFO:creating page");
        logger.debug("CREATE-DEBUG:creating page");
        Page newPage= pageRepository.create(page);

        if(newPage!=null)
        {
            return new ResponseEntity<Page>(newPage, HttpStatus.CREATED);
        }
        else {
            logger.error("CREATE-ERROR:Could not create page");
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

    }
    @GetMapping("{id}")
    public ResponseEntity<Page> read(@PathVariable long id) {

        logger.info("READ-INFO:Fetching page with id = " + id);
        logger.debug("READ-DEBUG:Fetching page with id = " + id);

        Page page = pageRepository.read(id);
        if(page!=null)
            return new ResponseEntity<Page>(page, HttpStatus.OK);
        else {
            logger.error("READ-ERROR:Could not find page with id = " + id);
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping
    public ResponseEntity<List<Page>> list() {

        logger.info("LIST-INFO:listing pages");
        logger.debug("LIST-DEBUG:listing pages");
        List<Page> pages= pageRepository.list();

        if(pages!=null) {
            return new ResponseEntity<List<Page>>(pages, HttpStatus.OK);
        }
        else {
            logger.error("LIST" +
                    "-ERROR:Could not list page");
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }
    @PutMapping("{id}")
    public ResponseEntity<Page> update(@RequestBody Page page, @PathVariable long id) {

        logger.info("UPDATE-INFO:updating pages with id: "+id);
        logger.debug("UPDATE-DEBUGupdating pages with id: "+id);
        Page updatedPage= pageRepository.update(page,id);
        if(updatedPage!=null)
            return new ResponseEntity<Page>(updatedPage, HttpStatus.OK);
        else {
            logger.error("UPDATE" +
                    "-ERROR:Could not update page");
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("{id}")
    public ResponseEntity delete(@PathVariable long id) {

        logger.info("DELETE-INFO:deleting pages with id: "+id);
        logger.debug("DELETE-INFO:deleting pages with id: "+id);

        pageRepository.delete(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}