(: XQuery by Priscilla Walmsley (c) O'Reilly Media 2007 :)
(: Example 25-5. Emulating templates with user-defined functions :)

declare function local:apply-templates($nodes as node()*) as node()* {
  for $node in $nodes
  return typeswitch ($node)
        case element(p) return local:p-template($node)
        case element(b) return local:b-template($node)
        case element(i) return local:i-template($node)
        case element() return local:apply-templates($node/(@*|node()))
        default return $node
};
declare function local:p-template($node as node()) as node()* {
   <para>{local:apply-templates($node/(@*|node()))}</para>
};
declare function local:b-template($node as node()) as node()* {
   <Strong>{local:apply-templates($node/(@*|node()))}</Strong>
};
declare function local:i-template($node as node()) as node()* {
   <Italics>{local:apply-templates($node/(@*|node()))}</Italics>
};
local:apply-templates(doc("p.xml")/p)
