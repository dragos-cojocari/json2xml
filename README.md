# json2xml
Command line utility to convert JSON to XML. Requires Java 7

> THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.


Given a JSON string as input the tool will create an XML and a mtaching XSD for it.

For the JSON->XML conversion the tool uses [org.json](http://www.json.org/java/) Check the project's page for known issues and limitations
For the XML->XSD conversion the tool uses [Apache XML Beans](https://xmlbeans.apache.org/) Check the project's page for known issues and limitatiosn

Command line syntax:
```
    java -jar json2xml.jar <jsonInputFile> <xmlOutputFolder>
```    

Example:
```
    java -jar json2xml.jar d:\test\test1.json d:\test\
```    
