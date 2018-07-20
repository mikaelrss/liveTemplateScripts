# LiveTemplateScripts
A collection of groovy scripts which could be useful for live templates in IntelliJ IDEA

#### DefaultPropsGenerator

A script for extracting flow-typed Props definition in Component file and mapping it to an object with values in the test file.

![Alt Text](http://g.recordit.co/1jgt67Xq0S.gif)

A component with props definition like this
```js
type Props = {
  componentNeeds2DArray: Array<Array<number>>,
  objectId: string,
  iNeedSomeNumbers: number,
  startDate: string,
  arrayWithCustomObject: Array<CustomType>,
  arrayWithNumbers: Array<number>,
  func: Function,
  function: () => void,
  functionWithPromise: (test: string) => Promise<void>
};
```

will return 

```js 
const defaultProps = {
  componentNeeds2DArray: [[77], [81]],
  objectId: 'testString',
  iNeedSomeNumbers: 43,
  startDate: 'testString',
  arrayWithCustomObject: [{}],
  arrayWithNumbers: [74, 15],
  func: jest.fn(),
  function: jest.fn(),
  functionWithPromise: jest.fn().mockImplementationOnce(() => Promise.resolve())
};
```


Usage in template: 

```const defaultProps = $DEFAULT_PROPS$```

`$DEFAULT_PROPS$` 


Needs to be set with path to file.

`groovyScript("/path/to/repo/defaultPropsGenerator.groovy", "path/to/MyComponent.js")`

The script takes one parameter, which is the path to the file adjacent to the test file currently in. The script works out of the box if the test file is in the same directory as the file where component is defined
and are named with the following pattern: `MyComponent.js`, `MyComponent.test.js`

to pass the relative path of the current test file to the script, another groovyScript is used.

`groovyScript("_editor.getVirtualFile().getPath()")`

