package com.vfergus.common.recyclerView.decoration.spacing;

import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.val;

import com.vfergus.common.R;
import com.vfergus.common.recyclerView.decoration.spacing.span.DefaultSpanIndexLookup;
import com.vfergus.common.recyclerView.decoration.spacing.span.DefaultSpanLookup;
import com.vfergus.common.recyclerView.decoration.spacing.span.SpanIndexLookup;
import com.vfergus.common.recyclerView.decoration.spacing.span.SpanLookup;

public class SpacingItemDecoration extends RecyclerView.ItemDecoration {
    public static void setDecorationMode(
        @NonNull final View view, @NonNull final DecorationMode decorationMode) {
        view.setTag(R.id.tag_item_spacing_decoration_mode, decorationMode);
    }

    @NonNull
    public static DecorationMode getDecorationMode(@NonNull final View view) {
        final val tag = view.getTag(R.id.tag_item_spacing_decoration_mode);

        return tag instanceof DecorationMode ? (DecorationMode) tag : DecorationMode.ALL;
    }

    @NonNull
    public static Builder builder() {
        return new Builder();
    }

    @Override
    public void getItemOffsets(
        final Rect outRect,
        final View view,
        final RecyclerView parent,
        final RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);

        final boolean rtl = parent.getLayoutDirection() == View.LAYOUT_DIRECTION_RTL;
        final val decorationMode = getDecorationMode(view);

        final int itemCount = parent.getAdapter().getItemCount();
        final int spanCount = getSpanCount(parent);
        final int spanIndex = getSpanIndex(view);
        final int position = parent.getChildAdapterPosition(view);

        final boolean isFirstColumn = spanIndex == 0;
        final boolean isLastColumn = spanIndex == spanCount - 1;
        final boolean isFirstRow = position < spanCount;
        final boolean isLastRow = position > (itemCount - spanCount - 1);

        final val padding = isPaddingEnabled(decorationMode) ? getPadding() : Border.ZERO;
        final val spacing = isSpacingEnabled(decorationMode) ? getSpacing() : Border.ZERO;

        final int spacingLeft = spacing.getLeft();
        final int spacingRight = spacing.getRight();
        final int spacingTop = spacing.getTop();
        final int spacingBottom = spacing.getBottom();

        final int paddingLeft = padding.getLeft();
        final int paddingRight = padding.getRight();
        final int paddingTop = padding.getTop();
        final int paddingBottom = padding.getBottom();

        int bottom;
        int top;
        int left;
        int right;

        int bottomEdge = paddingBottom;
        int topEdge = paddingTop;
        int leftEdge = paddingLeft;
        int rightEdge = paddingRight;

        bottom = spacingBottom;
        top = spacingTop;

        left = spacingLeft;
        right = spacingRight;

        if (isBordersCollapseEnabled()) {
            bottom /= 2;
            top /= 2;

            left /= 2;
            right /= 2;
        }

        if (isEdgesEnabled()) {
            bottomEdge += spacingBottom;
            topEdge += spacingTop;
            leftEdge += spacingLeft;
            rightEdge += spacingRight;
        }

        if (isFirstRow) {
            top = topEdge;
        }

        if (isLastRow) {
            bottom = bottomEdge;
        }

        if (isFirstColumn) {
            left = leftEdge;
        }

        if (isLastColumn) {
            right = rightEdge;
        }

        outRect.bottom += bottom;
        outRect.top += top;

        outRect.left += rtl ? right : left;
        outRect.right += rtl ? left : right;
    }

    protected SpacingItemDecoration(
        @NonNull final Border padding,
        @NonNull final Border spacing,
        final boolean bordersCollapseEnabled,
        final boolean edgesEnabled,
        @NonNull final SpanLookup spanLookup,
        @NonNull final SpanIndexLookup spanIndexLookup) {
        this.padding = padding;
        this.spacing = spacing;
        this.bordersCollapseEnabled = bordersCollapseEnabled;
        this.edgesEnabled = edgesEnabled;
        this.spanLookup = spanLookup;
        this.spanIndexLookup = spanIndexLookup;
    }

    protected int getSpanCount(@NonNull final RecyclerView parent) {
        return getSpanLookup().getSpanCount(parent.getLayoutManager());
    }

    protected int getSpanIndex(@NonNull final View view) {
        return getSpanIndexLookup().getSpanIndex(view);
    }

    protected boolean isPaddingEnabled(@NonNull final DecorationMode decorationMode) {
        return decorationMode != DecorationMode.SPACING && decorationMode != DecorationMode.NONE;
    }

    protected boolean isSpacingEnabled(@NonNull final DecorationMode decorationMode) {
        return decorationMode != DecorationMode.PADDING && decorationMode != DecorationMode.NONE;
    }

    @Getter
    private final boolean bordersCollapseEnabled;

    @Getter
    private final boolean edgesEnabled;

    @NonNull
    @Getter(AccessLevel.PROTECTED)
    private final Border padding;

    @NonNull
    @Getter(AccessLevel.PROTECTED)
    private final Border spacing;

    @Getter
    @NonNull
    private final SpanIndexLookup spanIndexLookup;

    @Getter
    @NonNull
    private final SpanLookup spanLookup;

    public enum DecorationMode {
        ALL, PADDING, SPACING, NONE
    }

    @NoArgsConstructor
    @ToString(doNotUseGetters = true)
    public static class Border {
        public static final Border ZERO = new Border(0, 0, 0, 0);

        public Border(final int left, final int top, final int right, final int bottom) {
            this.left = left;
            this.top = top;
            this.right = right;
            this.bottom = bottom;
        }

        public Border(@NonNull final Border border) {

            this.left = border.left;
            this.top = border.top;
            this.right = border.right;
            this.bottom = border.bottom;
        }

        @Getter
        @Setter
        private int bottom;

        @Getter
        @Setter
        private int left;

        @Getter
        @Setter
        private int right;

        @Getter
        @Setter
        private int top;
    }

    public static class Builder {
        @NonNull
        public final Builder collapseBorders() {
            this.collapseBorders = true;

            return this;
        }

        @NonNull
        public final Builder enableEdges() {
            this.enableEdges = true;

            return this;
        }

        @NonNull
        public final Builder setHorizontalPadding(final int leftPadding, final int rightPadding) {
            final val padding = getPadding();
            padding.setLeft(leftPadding);
            padding.setRight(rightPadding);

            return this;
        }

        @NonNull
        public final Builder setHorizontalPadding(final int padding) {
            return setHorizontalPadding(padding, padding);
        }

        @NonNull
        public final Builder setHorizontalSpacing(final int spacing) {
            return setHorizontalSpacing(spacing, spacing);
        }

        @NonNull
        public final Builder setHorizontalSpacing(final int leftSpacing, final int rightSpacing) {
            final val spacing = getSpacing();
            spacing.setLeft(leftSpacing);
            spacing.setRight(rightSpacing);

            return this;
        }

        @NonNull
        public final Builder setPadding(final int padding) {
            setPadding(padding, padding, padding, padding);

            return this;
        }

        @NonNull
        public final Builder setPadding(
            final int leftPadding,
            final int topPadding,
            final int rightPadding,
            final int bottomPadding) {
            setVerticalPadding(topPadding, bottomPadding);
            setHorizontalPadding(leftPadding, rightPadding);

            return this;
        }

        @NonNull
        public final Builder setSpacing(final int spacing) {
            setSpacing(spacing, spacing, spacing, spacing);

            return this;
        }

        @NonNull
        public final Builder setSpacing(
            final int leftSpacing,
            final int topSpacing,
            final int rightSpacing,
            final int bottomSpacing) {
            setVerticalSpacing(topSpacing, bottomSpacing);
            setHorizontalSpacing(leftSpacing, rightSpacing);

            return this;
        }

        @NonNull
        public final Builder setSpanIndexLookup(@Nullable final SpanIndexLookup spanIndexLookup) {
            this.spanIndexLookup = spanIndexLookup;

            return this;
        }

        @NonNull
        public final Builder setSpanLookup(@Nullable final SpanLookup spanLookup) {
            this.spanLookup = spanLookup;

            return this;
        }

        @NonNull
        public final Builder setVerticalPadding(final int padding) {
            return setVerticalPadding(padding, padding);
        }

        @NonNull
        public final Builder setVerticalPadding(final int topPadding, final int bottomPadding) {
            final val padding = getPadding();
            padding.setTop(topPadding);
            padding.setBottom(bottomPadding);

            return this;
        }

        @NonNull
        public final Builder setVerticalSpacing(final int spacing) {
            return setVerticalSpacing(spacing, spacing);
        }

        @NonNull
        public final Builder setVerticalSpacing(final int topSpacing, final int bottomSpacing) {
            final val spacing = getSpacing();
            spacing.setTop(topSpacing);
            spacing.setBottom(bottomSpacing);

            return this;
        }

        @NonNull
        public SpacingItemDecoration build() {
            final val spanLookup =
                this.spanLookup != null ? this.spanLookup : new DefaultSpanLookup();
            final val spanIndexLookup =
                this.spanIndexLookup != null ? this.spanIndexLookup : new DefaultSpanIndexLookup();

            return new SpacingItemDecoration(
                getPadding(),
                getSpacing(),
                isCollapseBorders(),
                isEnableEdges(),
                spanLookup,
                spanIndexLookup);
        }

        @NonNull
        @Getter(AccessLevel.PROTECTED)
        private final Border padding = new Border();

        @NonNull
        @Getter(AccessLevel.PROTECTED)
        private final Border spacing = new Border();

        @Getter(AccessLevel.PROTECTED)
        private boolean collapseBorders = false;

        @Getter(AccessLevel.PROTECTED)
        private boolean enableEdges = false;

        @Getter
        @Nullable
        private SpanIndexLookup spanIndexLookup;

        @Getter
        @Nullable
        private SpanLookup spanLookup;
    }
}
