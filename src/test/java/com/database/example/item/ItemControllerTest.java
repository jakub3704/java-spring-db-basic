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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ItemControllerTest {

    @InjectMocks
    private ItemController itemController = new ItemController();

    @Mock
    private ItemService itemService;

    @Test
    public void getItemTest() {
        ItemDto itemDto = new ItemDto();

        when(itemService.getItem(7L)).thenReturn(itemDto);

        ItemDto result = itemController.getItem(7L);

        assertEquals(itemDto, result);
    }

    @Test
    public void createItemTest() {
        ItemDto itemDto = new ItemDto();

        when(itemService.createItem(itemDto)).thenReturn(itemDto);

        ItemDto result = itemController.createItem(itemDto);

        assertEquals(itemDto, result);
    }

    @Test
    public void updateItemTest() {
        ItemDto itemDto = new ItemDto();

        when(itemService.updateItem(itemDto)).thenReturn(itemDto);

        ItemDto result = itemController.updateItem(itemDto);

        assertEquals(itemDto, result);
    }
}
