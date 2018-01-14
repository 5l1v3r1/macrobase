/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package edu.stanford.futuredata.macrobase.sql.tree;

import java.util.List;
import java.util.Optional;

public abstract class SetOperation extends QueryBody {

    private final boolean distinct;

    protected SetOperation(Optional<NodeLocation> location, boolean distinct) {
        super(location);
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    @Override
    public <R, C> R accept(AstVisitor<R, C> visitor, C context) {
        return visitor.visitSetOperation(this, context);
    }

    public abstract List<Relation> getRelations();

    @Override
    public Select getSelect() {
        return SELECT_ALL;
    }

    @Override
    public Optional<Expression> getWhere() {
        return Optional.empty();
    }

    @Override
    public Optional<OrderBy> getOrderBy() {
        return Optional.empty();
    }

    @Override
    public Optional<String> getLimit() {
        return Optional.empty();
    }
}
