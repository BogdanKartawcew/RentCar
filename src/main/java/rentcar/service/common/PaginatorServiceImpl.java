package rentcar.service.common;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Service("paginatorService")
@Transactional
public class PaginatorServiceImpl implements PaginatorService {
    @Override
    public ArrayList<String> getPaginatorTags(String link, int rowsOnPage, int pagesAmount, int pageNumber) {
        String tagActive = "<li class=\"active\"><a href=\"" + link;
        String tag1 = "<li><a href=\"" + link;
        String tag2 = "per" + rowsOnPage + "\">";
        String tag3 = "</a></li>";
        StringBuilder tag = new StringBuilder();
        ArrayList<String> paginatorTags = new ArrayList<>();
        for (int i = 1; i <= pagesAmount; i++) {
            if (i == pageNumber) {
                tag.append(tagActive);
            } else {
                tag.append(tag1);
            }
            tag.append(i);
            tag.append(tag2);
            tag.append(i);
            tag.append(tag3);
            paginatorTags.add(tag.toString());
            tag.setLength(0);
        }
        return paginatorTags;
    }

    @Override
    public int pagesAmountCounter(long pageCount, int rowsOnPage) {
        return (int) Math.ceil((double) pageCount / rowsOnPage);
    }
}
