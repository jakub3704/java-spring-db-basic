/****************************************************************************
 * Copyright 2020 Jakub Koczur
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, 
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES  
 * OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NON INFRINGEMENT. 
 * IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
 * DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT,  
 * TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE 
 * OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 * 
 *****************************************************************************/

package com.database.example.item;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@AutoConfigureMockMvc
@WebAppConfiguration
@SpringBootTest
public class ItemControllerIntegrationTest {

	@Autowired
	private ItemRepository itemRepository;
	
    @Autowired
    private MockMvc mvc;
	
    private List<Item> items = new ArrayList<>();
    
    @BeforeEach
    public void beforeEach() {
    	Item a = new Item();
    	a.setName("A Name");
    	a.setDescription("A Description");
    	
    	Item b = new Item();
    	b.setName("B Name");
    	b.setDescription("B Description");
    	
    	items.add(itemRepository.save(a));
    	items.add(itemRepository.save(b));
    }
    
    @AfterEach
    public void afterEach() {
    	itemRepository.deleteAll(items);
    }
    
    @Test
    public void getItemTest() throws Exception {
    	   ResultActions result = mvc.perform(
    			   MockMvcRequestBuilders.get("/item/" + items.get(1).getId())
    			   .header("Content-Type", "application/json;charset=UTF-8"));

           result.andExpect(status().isOk());

           result.andExpect(jsonPath("$.id", is(items.get(1).getId().intValue())))
                 .andExpect(jsonPath("$.name", is("B Name")))
           	     .andExpect(jsonPath("$.description", is("B Description")));
    }
    
    @Test
    public void createItemTest() throws Exception {
    	   ResultActions result = mvc.perform(
    			   MockMvcRequestBuilders.post("/item")
    			   .contentType("application/json;charset=UTF-8")
    			   .content("{\"name\": \"New Name\", \"description\": \"New Description\"}"  ));
    	   
           result.andExpect(status().isOk());

           result.andExpect(jsonPath("$.name", is("New Name")))
           	     .andExpect(jsonPath("$.description", is("New Description")));
    }
    
    @Test
    public void updateItemTest() throws Exception {
    	   ResultActions result = mvc.perform(
    			   MockMvcRequestBuilders.put("/item")
    			   .contentType("application/json;charset=UTF-8")
    			   .content("{\"id\": " + items.get(1).getId() + ", \"name\": \"New Name\", \"description\": \"New Description\"}"));
    	   
           result.andExpect(status().isOk());

           result.andExpect(jsonPath("$.id", is(items.get(1).getId().intValue())))
           		 .andExpect(jsonPath("$.name", is("New Name")))
           	     .andExpect(jsonPath("$.description", is("New Description")));
    }
    
}
