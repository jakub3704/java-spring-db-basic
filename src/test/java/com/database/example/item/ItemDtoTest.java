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

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class ItemDtoTest {

    @Test
    public void equalsTest() {
        ItemDto a = new ItemDto();
        a.setId(10L);
        a.setName("MyExistingName");
        a.setDescription("MyExistingDescription");

        ItemDto b = new ItemDto();
        b.setId(10L);
        b.setName("MyExistingName");
        b.setDescription("MyExistingDescription");

        assertTrue(a.equals(b));
    }

    @Test
    public void equalsTest_sameObj() {
        ItemDto a = new ItemDto();
        a.setId(10L);
        a.setName("MyExistingName");
        a.setDescription("MyExistingDescription");

        ItemDto b = a;

        assertTrue(a.equals(b));
    }

    @Test
    public void equalsTest_false() {
        ItemDto a = new ItemDto();
        a.setId(10L);
        a.setName("MyExistingName");
        a.setDescription("MyExistingDescription");

        ItemDto b = new ItemDto();
        b.setId(9L);
        b.setName("MyExistingName");
        b.setDescription("MyExistingDescription");

        assertFalse(a.equals(b));
    }

    @SuppressWarnings("unlikely-arg-type")
    @Test
    public void equalsTest_falseType() {
        ItemDto a = new ItemDto();
        a.setId(10L);
        a.setName("MyExistingName");
        a.setDescription("MyExistingDescription");

        Item b = new Item();

        assertFalse(a.equals(b));
    }
}
