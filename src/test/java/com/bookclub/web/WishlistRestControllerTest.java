package com.bookclub.web;

import com.bookclub.dao.WishlistDao;
import com.bookclub.model.WishlistItem;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(WishlistRestController.class)
public class WishlistRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private WishlistDao wishlistDao;

    @Test
    @WithMockUser(username = "testuser")
    public void testShowWishlist() throws Exception {
        WishlistItem item = new WishlistItem();
        item.setId("1");
        item.setTitle("Dune");
        item.setIsbn("12345");
        item.setAuthor("Frank Herbert");
        item.setUsername("testuser");

        when(wishlistDao.list("testuser")).thenReturn(List.of(item));

        mockMvc.perform(get("/api/wishlist"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value("1"))
                .andExpect(jsonPath("$[0].title").value("Dune"));
    }

    @Test
    @WithMockUser(username = "testuser")
    public void testFindById() throws Exception {
        WishlistItem item = new WishlistItem();
        item.setId("2");
        item.setTitle("Foundation");
        item.setIsbn("54321");
        item.setAuthor("Isaac Asimov");
        item.setUsername("testuser");

        when(wishlistDao.find("2")).thenReturn(item);

        mockMvc.perform(get("/api/wishlist/2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("2"))
                .andExpect(jsonPath("$.title").value("Foundation"));
    }
}
