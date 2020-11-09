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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemService {

    @Autowired
    ItemRepository itemRepository;
    
    public ItemDto createItem(ItemDto itemDto) {
    	Item item = new Item();
    	itemDtoToItem(itemDto, item);
        return itemToItemDto(itemRepository.save(item));    
    }
    
    public ItemDto updateItem(ItemDto itemDto) {
    	Item item = itemRepository.findById(itemDto.getId()).orElseThrow(
    			() -> new RuntimeException("- Item not found ( " + itemDto.getId() +" ) -"));
    	itemDtoToItem(itemDto, item);
        return itemToItemDto(itemRepository.save(item));    
    }
    
    public ItemDto getItem(Long id) {
        Item item = itemRepository.findById(id).orElseThrow(
               () -> new RuntimeException("- Item not found ( " + id +" ) -"));
        return itemToItemDto(item);     
    }  
    
    private Item itemDtoToItem(ItemDto itemDto, Item item) {
        item.setName(itemDto.getName());
        item.setDescription(itemDto.getDescription());
        return item;
    }
    
    private ItemDto itemToItemDto(Item item) {
        ItemDto itemDto = new ItemDto();
        itemDto.setId(item.getId());
        itemDto.setName(item.getName());
        itemDto.setDescription(item.getDescription());
        return itemDto;
    }   
}
