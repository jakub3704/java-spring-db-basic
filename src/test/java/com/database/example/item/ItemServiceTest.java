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

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.when;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
public class ItemServiceTest {

    @InjectMocks
    private ItemService itemService = new ItemService();
    
    @Mock
    private ItemRepository itemRepository;
    
    @Test
    public void createItemTest() {
    	// Data preparation
    	ItemDto itemDtoInput = new ItemDto();
    	itemDtoInput.setName("MyName_");
    	itemDtoInput.setDescription("MyDescription_");
    	
    	Item savedItem = new Item();
    	savedItem.setId(10L);
    	savedItem.setName("MyName");
    	savedItem.setDescription("MyDescription");
    	
    	// Mock setting
    	when(itemRepository.save(any(Item.class))).thenReturn(savedItem);
    	
    	// Method Testing
    	ItemDto itemDtoResult = itemService.createItem(itemDtoInput);
    	
    	// Assert Test Results
    	assertEquals(Long.valueOf(10L), itemDtoResult.getId());
    	assertEquals("MyName", itemDtoResult.getName());
    	assertEquals("MyDescription", itemDtoResult.getDescription());
    }
    
    @Test
    public void updateItemTest() {
    	// Data preparation
    	Item existingItem = new Item();
    	existingItem.setId(10L);
    	existingItem.setName("MyExistingName");
    	existingItem.setDescription("MyExistingDescription");
    	
    	ItemDto itemDtoInput = new ItemDto();
    	itemDtoInput.setId(10L);
    	itemDtoInput.setName("MyNewName");
    	itemDtoInput.setDescription("MyNewDescription");
    	
    	Item savedItem = new Item();
    	savedItem.setId(10L);
    	savedItem.setName("MyNewName");
    	savedItem.setDescription("MyNewDescription");
    	
    	// Mock setting
    	when(itemRepository.findById(itemDtoInput.getId())).thenReturn(Optional.of(existingItem));
    	when(itemRepository.save(any(Item.class))).thenReturn(savedItem);
    	
    	// Method Testing
    	ItemDto itemDtoResult = itemService.updateItem(itemDtoInput);
    	
    	// Assert Test Results
    	assertEquals(Long.valueOf(10L), itemDtoResult.getId());
    	assertEquals("MyNewName", itemDtoResult.getName());
    	assertEquals("MyNewDescription", itemDtoResult.getDescription());
    }
    
    @Test
    public void updateItemTest_notFound() {	
    	// Data preparation
    	ItemDto itemDtoInput = new ItemDto();
    	itemDtoInput.setId(10L);
    	
    	// Mock setting
    	when(itemRepository.findById(10L)).thenReturn(Optional.empty());
    	
    	// Method Testing and Assert Throws
        assertThrows(RuntimeException.class, () -> itemService.updateItem(itemDtoInput));
    }
    
    @Test
    public void getItemTest() {
    	// Data preparation    	
    	Item existingItem = new Item();
    	existingItem.setId(10L);
    	existingItem.setName("MyExistingName");
    	existingItem.setDescription("MyExistingDescription");
    	
    	// Mock setting
    	when(itemRepository.findById(10L)).thenReturn(Optional.of(existingItem));
    	
    	// Method Testing
    	ItemDto itemDtoResult = itemService.getItem(10L);
    	
    	// Assert Test Results
    	assertEquals(Long.valueOf(10L), itemDtoResult.getId());
    	assertEquals("MyExistingName", itemDtoResult.getName());
    	assertEquals("MyExistingDescription", itemDtoResult.getDescription());
    }
    
    @Test
    public void getItemTest_notFound() {	
    	// Mock setting
    	when(itemRepository.findById(10L)).thenReturn(Optional.empty());
    	
    	// Method Testing and Assert Throws
        assertThrows(RuntimeException.class, () -> itemService.getItem(10L));
    }
}
