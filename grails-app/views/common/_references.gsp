<div id="contributors">
    <h1>My references</h1>
    <ol>
        <g:each in="${myReferences}" var="c">

            <li>${c.lastName},${c.firstInitial} (${c.year})</li>
        </g:each>
    </ol>
</div>